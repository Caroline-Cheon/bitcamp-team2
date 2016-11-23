package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherUpdateController implements Command {

  private TeacherDao teacherDao;

  public TeacherUpdateController() {
    teacherDao = TeacherDao.getInstance();
  }


 // teacher/update?name=홍길동&gender=남&age=27&career=20년&skill=java&major=컴퓨터공학&email=hong@test.com&tel=111-1111
  public void service(HashMap<String,String> paramMap, PrintStream out) {

    if(!teacherDao.existName(paramMap.get("name"))) {
      out.println("해당 강사를 찾지 못했습니다.");
      return;
    }
    Teacher teacher = new Teacher();
    teacher.setName(paramMap.get("name"));
    teacher.setGender(paramMap.get("gender"));
    teacher.setAge(Integer.parseInt(paramMap.get("age")));
    teacher.setCareer(paramMap.get("career"));
    teacher.setSkill(paramMap.get("skill"));
    teacher.setMajor(paramMap.get("major"));
    teacher.setEmail(paramMap.get("email"));
    teacher.setTel(paramMap.get("tel"));

    teacherDao.update(teacher);
    out.println("강사 정보를 변경 하였습니다.");

  }

}
