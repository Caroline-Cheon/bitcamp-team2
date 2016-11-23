package bitcamp.java89.ems.server.controller.TeacherController;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherViewController implements Command {

  private TeacherDao teacherDao;

  public TeacherViewController() {
    teacherDao = TeacherDao.getInstance();
  }


 // teacher/view?name=???
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    if(!teacherDao.existName(paramMap.get("name"))) {
      out.println("해당 강사가 없습니다.");
      return;
   }

   Teacher teacher = teacherDao.getOne(paramMap.get("name"));
   out.printf("아이디: %s\n", teacher.getName());
   out.printf("성별: %s\n", teacher.getGender());
   out.printf("나이: %d\n", teacher.getAge());
   out.printf("경력: %s\n", teacher.getCareer());
   out.printf("능력: %s\n", teacher.getSkill());
   out.printf("전공: %s\n", teacher.getMajor());
   out.printf("이메일: %s\n", teacher.getEmail());
   out.printf("전화: %s\n", teacher.getTel());
   return;
 }

}
