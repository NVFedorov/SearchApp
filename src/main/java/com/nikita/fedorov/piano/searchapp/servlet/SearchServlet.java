package com.nikita.fedorov.piano.searchapp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nikita.fedorov.piano.searchapp.bean.QuestionBean;
import com.nikita.fedorov.piano.searchapp.util.QuestionsParser;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {

	private static final String URL = "http://api.stackexchange.com/2.2/search";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchParam = request.getParameter("param");
		if (searchParam.isEmpty()) {
			request.setAttribute("success", false);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		StringBuilder strBuf = new StringBuilder();
		String parametrizedUrl = URL + "?order=desc&sort=activity&site=stackoverflow&intitle=" + searchParam;
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		try {
			URL url = new URL(parametrizedUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				request.setAttribute("success", false);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
				return;
			}

			reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream()), "UTF-8"));
			String output = null;
			while ((output = reader.readLine()) != null) {
				strBuf.append(output);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}

		if (strBuf.length() < 1) {
			request.setAttribute("success", false);
		} else {
			ArrayList<QuestionBean> questions = QuestionsParser.ParseQuestions(strBuf.toString());
			request.setAttribute("success", true);
			request.setAttribute("questions", questions);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

}
