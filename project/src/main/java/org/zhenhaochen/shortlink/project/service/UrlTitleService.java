package org.zhenhaochen.shortlink.project.service;

/**
 * URL Title Interface Layer
 */
public interface UrlTitleService {

    /**
     * get title by url
     *
     * @param url target url
     * @return title
     */
    String getTitleByUrl(String url);
}

