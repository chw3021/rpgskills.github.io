package io.github.chw3021.commons;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class ConfigManager {

    private static ConfigManager instance;
    private File customConfigFile;
    private FileConfiguration customConfig;
    private JavaPlugin plugin;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        createCustomConfig();
    }
    
    public static ConfigManager getInstance(JavaPlugin plugin) {
        if (instance == null) {
            instance = new ConfigManager(plugin);
        }
        return instance;
    }
    
    public void createCustomConfig() {
        customConfigFile = new File(plugin.getDataFolder(), "Config/config.yml");

        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            // 기본 설정 추가
            customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
            customConfig.set("Language", "ko_kr");
            customConfig.set("Worlds", Arrays.asList()); // 초기 비어있는 리스트 설정
            saveCustomConfig(); // 여기서 기본 config.yml 파일을 저장
        } else {
            customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
            // 이미 존재하는 경우에는 추가적인 처리를 할 수 있음
            if (!customConfig.contains("Language")) {
                customConfig.set("Language", "ko_kr");
            }
            if (!customConfig.contains("Worlds")) {
                customConfig.set("Worlds", Arrays.asList());
            }
        }
    }
    

    public FileConfiguration getCustomConfig() {
        return customConfig;
    }

    public void saveCustomConfig() {
        try {
            customConfig.save(customConfigFile);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
    
    public ConfigManager(File customConfigFile) {
        this.customConfigFile = customConfigFile;
    }
    public void saveConfigWithComments() {
        try {
            // 새로운 내용을 담을 StringBuilder
            StringBuilder newContent = new StringBuilder();

            // 주석과 새로운 값을 추가
            newContent.append("# Set the Server Language\n")
                      .append("# English - en_us, 한국어 - ko_kr\n")
                      .append("Language: ").append(getCustomConfig().getString("Language")).append("\n")
                      .append("\n\n\n")
                      .append("# Add worlds to this list to disable monster, NPC spawns\n")
                      .append("Worlds: ").append(getCustomConfig().getStringList("Worlds")).append("\n");

            // 기존 내용 가져오기
            try (BufferedReader reader = new BufferedReader(new FileReader(customConfigFile))) {
                String line;
                boolean languageWritten = false;
                boolean worldsWritten = false;
                
                // 기존 파일을 읽으면서 중복 방지
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Language:")) {
                        languageWritten = true;  // 이미 언어 설정이 있음
                    }
                    if (line.contains("Worlds:")) {
                        worldsWritten = true;  // 이미 Worlds 설정이 있음
                    }
                }
                
                // 중복되지 않는 경우에만 새로 추가
                if (!languageWritten) {
                    newContent.append("Language: ").append(getCustomConfig().getString("Language")).append("\n");
                }
                if (!worldsWritten) {
                    newContent.append("Worlds: ").append(getCustomConfig().getStringList("Worlds")).append("\n");
                }
            }

            // 새로 생성된 내용을 파일에 기록
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(customConfigFile))) {
                writer.write(newContent.toString());
            }

        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

}
