package com.homework.nix;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "unique-ip-handler", urlPatterns = "/unique")
public class UniqueIpHandlerServlet extends HttpServlet {
	
	private final Map<String, String> uniqueIp = new HashMap<>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter responseBody = resp.getWriter();
		
		
		String ip = req.getRemoteHost();
		String header = req.getHeader("User-Agent");
		uniqueIp.put(ip, header);
		
		resp.setContentType("text/html");
		
		responseBody.println("<h1 align=\"center\">Unique requests</h1>");
		uniqueIp.forEach((k, v) -> {
			if(!k.equals(req.getRemoteHost())) {
				responseBody.println("<p align=\"center\">" + k + "</p>");
				responseBody.println("<p align=\"center\">" + v + "</p>");
			}
		});
	}
}
