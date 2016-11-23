package bitcamp.java89.ems.server.controller.TeacherController;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherListController implements Command {
 
  private TeacherDao teacherDao;

  public TeacherListController() {
    teacherDao = TeacherDao.getInstance();
  }


 // teacher/list
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    ArrayList<Teacher> list = teacherDao.getList();
    for (Teacher teacher : list) {
      out.printf(" %s,%s,%d,%s,%s,%s,%s,%s\n",
          teacher.getName(),
          teacher.getGender(),
          teacher.getAge(),
          teacher.getCareer(),
          teacher.getSkill(),
          teacher.getMajor(),
          teacher.getEmail(),
          teacher.getTel());
    }
  }
  

}




