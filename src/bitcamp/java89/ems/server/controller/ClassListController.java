// 강의실

package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.ClassDao;
import bitcamp.java89.ems.server.vo.ClassRoom;;

public class ClassListController implements Command {
  private ClassDao classDao;

  public ClassListController() throws Exception {
    classDao = ClassDao.getInstance();
  }

  // 강의실 데이터 출력
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    ArrayList<ClassRoom> list = classDao.getList();
    try {
      for (ClassRoom classRoom : list) {
        out.printf("%s, %s, %s, %d, %d, %b\n", classRoom.getClassName(), classRoom.getFloorInfo(),
            classRoom.getCapacity(), classRoom.getCabinetQty(), classRoom.getAirconditionerQty(),
            classRoom.isProjectBim());
      }
    } catch (Exception e) {
      out.println("List 출력 중 에러");
    }
  }
}
