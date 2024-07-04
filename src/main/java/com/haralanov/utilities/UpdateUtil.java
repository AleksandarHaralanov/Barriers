package com.haralanov.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.bukkit.Bukkit.getLogger;

public class UpdateUtil {

    /**
     * Checks for updates by querying a given URL and comparing the current version with the latest available version.
     * <p><b>Caution:</b> This method only works with GitHub repositories. You will need to modify it if you use something else.</p>
     * <b>Warning:</b> The method automatically appends 'v' to the front of your plugin's current version to comply
     * with GitHub's semantic versioning conventions. If your plugin's release tag does not have 'v' in front of the
     * version, the plugin will not recognize it.
     * <hr>
     * @param pluginName     The name of the plugin.
     * @param currentVersion The current version of the plugin.
     * @param githubApiUrl      The URL to query for the latest release information.
     *                       <p>E.g., 'https://api.github.com/repos/USER/REPO/releases/latest'.</pr>
     */
    public static void checkForUpdates(String pluginName, String currentVersion, String githubApiUrl) {
        currentVersion = "v" + currentVersion;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(githubApiUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                getLogger().warning(String.format("[%s] Unexpected code: %s", pluginName, responseCode));
                getLogger().warning(String.format("[%s] Unable to check if plugin has a newer version.", pluginName));
                return;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            String responseBody = content.toString();
            String latestVersion = getLatestVersion(responseBody);
            compareVersions(pluginName, currentVersion, latestVersion, githubApiUrl);
        } catch (IOException e) {
            getLogger().severe(String.format("[%s] IOException occurred while checking for a new version: %s", pluginName, e.getMessage()));
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static String getLatestVersion(String responseBody) {
        String tagNameField = "\"tag_name\":\"";

        int tagIndex = responseBody.indexOf(tagNameField);
        if (tagIndex == -1) {
            return null;
        }

        int startIndex = tagIndex + tagNameField.length();
        int endIndex = responseBody.indexOf("\"", startIndex);
        if (endIndex == -1) {
            return null;
        }

        return responseBody.substring(startIndex, endIndex);
    }

    private static void compareVersions(String pluginName, String currentVersion, String latestVersion, String downloadLink) {
        if (latestVersion == null) {
            getLogger().warning(String.format("[%s] Could not determine the latest version.", pluginName));
            return;
        }

        if (!currentVersion.equalsIgnoreCase(latestVersion)) {
            downloadLink = downloadLink.replace("api.github.com/repos", "github.com");
            getLogger().info(String.format("[%s] New %s available, you are running an OUTDATED %s!", pluginName, latestVersion, currentVersion));
            getLogger().info(String.format("[%s] Download the latest version from: %s", pluginName, downloadLink));
        } else {
            getLogger().info(String.format("[%s] You are running the latest version.", pluginName));
        }
    }
}