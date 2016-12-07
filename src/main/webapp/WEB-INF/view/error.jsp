<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/utils/header.jsp" />
<div class="jumbotron text-center">
  <h1>ERREUR!</h1>
  <p>Désolé pour cetter erreur. Il semble que le site ne marche plus...</p>
  <p>
    <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/" role="button">Back to home</a>
  </p>
</div>
<jsp:include page="/WEB-INF/utils/footer.jsp" />
