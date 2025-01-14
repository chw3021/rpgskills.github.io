package io.github.chw3021.commons;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class ResourcePackManager implements Listener{

	@EventHandler
	public void onResourcePackStatus(PlayerResourcePackStatusEvent event) {
        System.out.println(event.getID());
        System.out.println(event.getStatus());
	}

	public static void fixZipStructure(String zipFilePath) throws IOException {
	    Path tempDir = Files.createTempDirectory("resourcepack_temp");
	    File zipFile = new File(zipFilePath);

	    // 1. 압축 풀기
	    try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
	        ZipEntry entry;
	        while ((entry = zis.getNextEntry()) != null) {
	            File file = new File(tempDir.toFile(), entry.getName());
	            if (entry.isDirectory()) {
	                file.mkdirs();
	            } else {
	                file.getParentFile().mkdirs();
	                try (FileOutputStream fos = new FileOutputStream(file)) {
	                    byte[] buffer = new byte[1024];
	                    int length;
	                    while ((length = zis.read(buffer)) > 0) {
	                        fos.write(buffer, 0, length);
	                    }
	                }
	            }
	        }
	    }

	    // 2. 압축 구조 확인 및 수정
	    File[] files = tempDir.toFile().listFiles();
	    if (files != null && files.length == 1 && files[0].isDirectory()) {
	        File innerDir = files[0]; // 최상위 폴더 내부
	        File[] innerFiles = innerDir.listFiles();
	        if (innerFiles != null) {
	            for (File file : innerFiles) {
	                Files.move(file.toPath(), tempDir.resolve(file.getName())); // 최상위로 이동
	            }
	        }
	        Files.delete(innerDir.toPath()); // 불필요한 폴더 삭제
	    }

	    // 3. 재압축
	    try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
	        compressDirectory(tempDir.toFile(), "", zos);
	    }
	}
	
	// 디렉토리 압축 메서드
	private static void compressDirectory(File dir, String basePath, ZipOutputStream zos) throws IOException {
	    File[] files = dir.listFiles();
	    if (files == null) return;

	    for (File file : files) {
	        String entryName = basePath.isEmpty() ? file.getName() : basePath + "/" + file.getName();
	        if (file.isDirectory()) {
	            compressDirectory(file, entryName, zos);
	        } else {
	            try (FileInputStream fis = new FileInputStream(file)) {
	                ZipEntry entry = new ZipEntry(entryName);
	                zos.putNextEntry(entry);

	                byte[] buffer = new byte[1024];
	                int length;
	                while ((length = fis.read(buffer)) > 0) {
	                    zos.write(buffer, 0, length);
	                }
	            }
	        }
	    }
	}
	
	public static byte[] calculateSHA1(String filePath) throws Exception {
	    MessageDigest digest = MessageDigest.getInstance("SHA-1");

	    try (InputStream fis = new FileInputStream(filePath)) {
	        byte[] buffer = new byte[1024];
	        int bytesRead;

	        while ((bytesRead = fis.read(buffer)) != -1) {
	            digest.update(buffer, 0, bytesRead);
	        }
	    }

	    return digest.digest();
	}
	private static String bytesToHex(byte[] bytes) {
	    StringBuilder hexString = new StringBuilder();
	    for (byte b : bytes) {
	        String hex = Integer.toHexString(0xff & b);
	        if (hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}

    public byte[] getSHA1Hash(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        return digest.digest(data); // Base64 인코딩 없이 바이트 배열 반환
    }
    
    public static byte[][] downloadResourcePack(String urlString) throws Exception {
        URI uri = new URI(urlString);
        URL url = uri.toURL();
        String zipFilePath = "RpsSkills.zip";

        // 1. 파일 다운로드
        try (InputStream in = new BufferedInputStream(url.openStream());
             FileOutputStream out = new FileOutputStream(zipFilePath)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                out.write(dataBuffer, 0, bytesRead);
            }
        }

        // 2. 압축 구조 수정
        fixZipStructure(zipFilePath);

        // 3. 수정된 파일의 SHA-1 해시 계산
        byte[] modifiedHash = calculateSHA1(zipFilePath);

        System.out.println("Modified SHA-1 hash: " + bytesToHex(modifiedHash));

        // 4. 수정된 파일 반환
        return new byte[][]{Files.readAllBytes(Paths.get(zipFilePath)), modifiedHash};
    }
	
    //@EventHandler
    public void join(PlayerJoinEvent ev) {
        Player p = ev.getPlayer();

        try {
            String resourcePackUrl = "https://github.com/chw3021/RpgSkills/archive/refs/heads/master.zip";
            byte[][] resourcePackData = downloadResourcePack(resourcePackUrl);

            byte[] modifiedFileData = resourcePackData[0];
            byte[] modifiedHash = resourcePackData[1];

            // 변환된 파일의 해시를 Minecraft 클라이언트에 전달
            p.setResourcePack(resourcePackUrl);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
