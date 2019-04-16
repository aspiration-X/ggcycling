package com.spir.ggcycling.bean;

/**
 * created by spir
 * Date2019/4/14 Time 16:33
 */
public class CommentVo {
    Comment comment;
    User user;

    public CommentVo() {
    }

    public CommentVo(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
