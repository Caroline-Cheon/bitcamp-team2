package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.LectureDao;
import bitcamp.java89.ems.server.vo.Lecture;

public class LectureListController implements Command {
 
 private LectureDao lectureDao;
  

  public LectureListController() {
    lectureDao = LectureDao.getInstance();
  }
  
 
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    ArrayList<Lecture> list = lectureDao.getList();
    for (Lecture lecture : list) {
      out.printf("%s,%s,%d,%s\n",
        lecture.getName(),
        lecture.getIntroduce(),
        lecture.getLimit(),
        (lecture.isLevelTest() ? "y" : "n"));
    }
  }
}
