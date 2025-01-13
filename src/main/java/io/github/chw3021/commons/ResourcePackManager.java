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

import com.google.common.util.concurrent.ListenableFuture;

public class ResourcePackManager implements Listener{

	@EventHandler
	public void onResourcePackStatus(PlayerResourcePackStatusEvent event) {
	    Player player = event.getPlayer();
	    switch (event.getStatus()) {
	        case SUCCESSFULLY_LOADED:
	            player.sendMessage("Resource pack applied successfully!");
	            break;
	        case DECLINED:
	            player.sendMessage("Resource pack was declined.");
	            break;
	        case FAILED_DOWNLOAD:
	            player.sendMessage("Failed to download resource pack.");
	            break;
	        case ACCEPTED:
	            player.sendMessage("Resource pack accepted, downloading...");
	            break;
		default:
            System.out.println(event.toString());
            System.out.println(event.getID());
            System.out.println(event.getStatus());
			break;
	    }
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

        try (InputStream in = new BufferedInputStream(url.openStream());
             FileOutputStream out = new FileOutputStream(zipFilePath)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                out.write(dataBuffer, 0, bytesRead);
            }
        }

        // 2. SHA-1 해시 계산 (압축 수정 전)
        byte[] originalHash = calculateSHA1(zipFilePath); // 파일 다운로드 후 원본 해시 계산
        System.out.println("Original SHA-1 hash: " + bytesToHex(originalHash));

        // 3. 압축 구조 수정
        fixZipStructure(zipFilePath);

        // 4. 수정된 파일 반환 (Minecraft에서 원본 해시 전달)
        return new byte[][]{Files.readAllBytes(Paths.get(zipFilePath)), originalHash};  // 수정된 파일과 원본 해시 함께 반환
    }

    public void join(PlayerJoinEvent ev) {
        Player p = ev.getPlayer();

        try {
            String resourcePackUrl = "https://github.com/chw3021/RpgSkills/archive/refs/heads/master.zip";
            byte[][] result = downloadResourcePack(resourcePackUrl);  // 파일과 해시 값 받기
            byte[] data = result[0];  // 다운로드된 리소스팩
            byte[] originalHash = result[1];  // 원본 해시 값

            System.out.println("Original SHA-1 hash before download: " + bytesToHex(originalHash));
            System.out.println("data SHA-1 hash before download: " + bytesToHex(getSHA1Hash(data)));

            p.setResourcePack(resourcePackUrl, originalHash, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
