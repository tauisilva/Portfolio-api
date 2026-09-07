package dev.taui.portfolio_api.model;

import java.util.List;

public record Project(
        String id,
        String title,
        String summary,
        String description,
        List<String> tags,
        String repoUrl,
        String apiRepoUrl,
        String liveDemoUrl,
        boolean featured
) {
}
