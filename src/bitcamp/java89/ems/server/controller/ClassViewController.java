// 강의실

package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.ClassDao;
import bitcamp.java89.ems.server.vo.ClassRoom;;

public class ClassViewController implements Command {
  private ClassDao classDao;

  public ClassViewController() throws Exception {
    classDao = ClassDao.getInstance();
  }

  // 강의실 특정 데이터 출력
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    try {
      ArrayList<ClassRoom> list = classDao.getListByName(paramMap.get("cName"));
      for (ClassRoom classRoom : list) {
      out.printf("%s, %s, %d, %d, %d, %b\n", classRoom.getClassName(), classRoom.getFloorInfo(),
          classRoom.getCapacity(), classRoom.getCabinetQty(), classRoom.getAirconditionerQty(),
          classRoom.isProjectBim());
      }
    } catch (Exception e) {
      out.println("특정 데이터 조회 작업 중 에러 발생!");
    }
  }
}
