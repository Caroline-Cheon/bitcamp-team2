package bitcamp.java89.ems.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class RequestThread extends Thread {
  private Socket socket;
  private Scanner in;
  private PrintStream out;
  
  private HashMap<String, Command> commandMap;
  
  public RequestThread(Socket socket, HashMap<String, Command> commandMap) {
    this.socket = socket;
    this.commandMap = commandMap;
  }
  @Override
  public void run() {
    // 스레드가 독립적으로 실행할 코드를 두는 곳
    try {
      in = new Scanner(new BufferedInputStream(socket.getInputStream()));
      out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()), true);
      
//      textBookController = new TextBookController(in, out);
//      contactController = new ContactController(in, out); 
   
      out.println("비트캠프 관리시스템에 오신 걸 환영합니다.");
      
      
      while (true) {
        // 클라이언트에게 데이터를 전송한다.
        out.println("명령> ");
        out.println(); // 빈 줄은 데이터의 끝을 의미한다(프로토콜)
   
        boolean running = true;
        
        // 클라이언트가 보낸 명령문을 분석하여 파라미터 값을 분리 추출한다.
        String[] command = in.nextLine().split("\\?");
        
        HashMap<String,String> paramMap = new HashMap<>();
        //파라미터 문자열이 있다면, 이 문자열을 분석하여 HashMap에 보관한다.
        
        if (command.length == 2) {
          String[] params = command[1].split("&");
          for (String value : params) {
            String[] kv = value.split("=");
            paramMap.put(kv[0], kv[1]);
          }
        }
        
        Command commandHandler = commandMap.get(command[0]);
        
        if (commandHandler == null) {
          if (command[0].equals("quit")) {
            doQuit();
            break;
          }
          out.println("지원하지 않는 명령어입니다.");
          continue; // 다음 줄로 가지 않고 반복문 조건 검사로 건너뛴다.
        }
        // 클라이언트가 보낸 명령을 처리할 객체가 있다면, 작업을 실행한다.
        commandHandler.service(paramMap, out);
        
      } // while
    } catch (Exception e) {
      // 클라이언트 요청 처리 중 예외 발생!
    } finally {
      try {in.close();} catch (Exception e) {}
      try {out.close();} catch (Exception e) {}
      try {socket.close();} catch (Exception e) {}
    }
    
  }
  private void doMenu() {
    out.println("[메뉴]");
    out.println("1. 교재관리 ");
    out.println("5. 연락처관리 ");
    out.println("메뉴 이동은 'go 메뉴번호'를 입력하세요. ");
    out.println("[명령]");
    out.println("save   데이터 저장");
    out.println("quit   프로그램 종료");
  }
  private boolean doQuit() {
    doSave();
    System.out.println("클라이언트 연결 종료");
    return true;
  }

  private void doSave() {
    try {
      //textBookController.save();
    } catch (Exception e) {
      System.out.println("책 정보 저장 중에 오류가 발생했습니다.");
    }
    
    try {
    //  contactController.save();
    } catch (Exception e) {
      System.out.println("연락처 정보 저장 중에 오류가 발생했습니다.");
    }
  }
}