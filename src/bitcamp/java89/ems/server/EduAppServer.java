//버전 1.8
package bitcamp.java89.ems.server;

import java.net.ServerSocket;
import java.util.HashMap;

import bitcamp.java89.ems.server.controller.TeacherAddController;
import bitcamp.java89.ems.server.controller.TeacherDeleteController;
import bitcamp.java89.ems.server.controller.TeacherListController;
import bitcamp.java89.ems.server.controller.TeacherUpdateController;
import bitcamp.java89.ems.server.controller.TeacherViewController;
import bitcamp.java89.ems.server.controller.TextBookAddController;
import bitcamp.java89.ems.server.controller.TextBookDeleteController;
import bitcamp.java89.ems.server.controller.TextBookListController;
import bitcamp.java89.ems.server.controller.TextBookUpdateController;
import bitcamp.java89.ems.server.controller.TextBookViewController;

public class EduAppServer {
  // Command 구현체 보관소
  // HashMap<명령문자열, 요청처리객체> commandMap
  HashMap<String, Command> commandMap = new HashMap<>();

  public EduAppServer() {
    // 클라이언트 요청을 처리할 Command 구현체를 준비
    
    commandMap.put("textbook/add", new TextBookAddController());
    commandMap.put("textbook/list", new TextBookListController());
    commandMap.put("textbook/view", new TextBookViewController());
    commandMap.put("textbook/delete", new TextBookDeleteController());
    commandMap.put("textbook/update", new TextBookUpdateController());
    
    commandMap.put("teacher/list", new TeacherListController());
    commandMap.put("teacher/view", new TeacherViewController());
    commandMap.put("teacher/add", new TeacherAddController());
    commandMap.put("teacher/update", new TeacherUpdateController());
    commandMap.put("teacher/delete", new TeacherDeleteController());
    
  }
  private void service() throws Exception {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중");
    
    while (true) {
      new RequestThread(ss.accept(), commandMap).start();
    }
    //ss.close();
  }
  public static void main(String[] args) throws Exception {
    EduAppServer eduServer = new EduAppServer();
    eduServer.service();
  }
}
