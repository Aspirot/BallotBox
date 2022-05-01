package edu.colval.javase.ballotbox.forumservice.dal;

import edu.colval.javase.ballotbox.forumservice.bll.model.Forum;
import edu.colval.javase.ballotbox.forumservice.bll.model.Post;

import java.util.List;

public interface IForumDAO {
    void createForum(Forum forum);
    List<Forum> findAllForums();
    Forum findForumById(int id);
    boolean deleteForumById(int id);
}
