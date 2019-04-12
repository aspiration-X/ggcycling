package com.spir.ggcycling.bean;

/**
 * created by spir
 * Date2019/4/10 Time 21:59
 */
public class Vo {
    News news;
    User user;
    int like;

    public Vo(News news, User user, int like) {
        this.news = news;
        this.user = user;
        this.like = like;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
