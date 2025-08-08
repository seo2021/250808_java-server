import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.Part;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Servlet은 HttpServlet을 상속 받아야 함
// '/chat' 이라는 요청을 받을 수 있음
@WebServlet("/")
public class MyServlet extends HttpServlet {
    
    // 경로에 들어갔을 때 호출될 메소드
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // req -> 인풋
        //req.setAttribute("name", "김자바"); // key, value

        // GenAI
        //Dotenv dotenv = Dotenv.load();
        // .env 파일이 없으면 에러를 던지지 않도록 설정
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String apiKey = dotenv.get("GOOGLE_API_KEY");

        Client client = Client.builder()
                .apiKey(apiKey)
                .build();

        String data = client.models.generateContent(
                "gemini-2.0-flash"
                , "오늘 날씨에 어울리는 명언 하나만 짧게"
                , null
        ).candidates().get().get(0).content().get().text();

        req.setAttribute("data", data); // key, value

        // resp -> 아웃풋
        //resp.getWriter().println("Hello AI!");

        // WEB-INF에 forward
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/chat.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String question = req.getParameter("question");

        // GenAI
        //Dotenv dotenv = Dotenv.load();
        // .env 파일이 없으면 에러를 던지지 않도록 설정
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String apiKey = dotenv.get("GOOGLE_API_KEY");

        Client client = Client.builder()
                .apiKey(apiKey)
                .build();

        String answer = client.models.generateContent(
                "gemini-2.0-flash"
                , question
                , GenerateContentConfig.builder().systemInstruction(Content.builder().parts(Part.builder().text("100자 이내로 마크다운 없이 간결하게 평문으로"))).build()).text();

        req.setAttribute("answer", answer); // key, value
        req.setAttribute("question", question); // input에 넣었던 것을 재전달

        // WEB-INF에 forward
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/chat.jsp");
        dispatcher.forward(req, resp);
    }
}
