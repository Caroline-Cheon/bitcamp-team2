package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TeacherDao;

public class TeacherDeleteController implements Command {

  private TeacherDao teacherDao;

  public TeacherDeleteController() {
    teacherDao = TeacherDao.getInstance();
  }


 // teacher/delete?name=???
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    if (!teacherDao.existName(paramMap.get("name"))) {
      out.println("해당 데이터가 없습니다.");
      return;
    }

    teacherDao.delete(paramMap.get("name"));
    out.printf("%s 강사 정보를 삭제하였습니다.\n", paramMap.get("name"));
    }
  }
