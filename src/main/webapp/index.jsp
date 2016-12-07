<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header --%>
<jsp:include page="/WEB-INF/utils/header.jsp" />

<%-- import scripts --%>
<script src="${pageContext.request.contextPath}/resources/js/index.js"></script>

<%-- content --%>
<div class="page-header">
  <h1>
    Smart P3 <small><br />Prévention et Protection des Populations</small>
  </h1>
</div>
<div class="row">
  <div class="col-md-7">
    <div class="media">
      <div class="media-left">
        <a href="#"> <img class="media-object" src="${pageContext.request.contextPath}/resources/image/wifi-icon-sm.jpg" alt="logo" height="64" width="64">
        </a>
      </div>
      <div class="media-body">
        <h4 class="media-heading">Transmission sans fil</h4>
        <p class="text-justify hidden-xs">Solution Libelium IoT nous aide à déployer les capteurs d'une manière rapide et facile. Leurs capteurs peuvent être placés dans un endroit approprié, en
          utilisant Zigbee, 3G et Ethernet. Le réseau de capteurs peut s'étendre à une zone au sein de sept kilomètres. Pendant l'incendie, ils présentent une excellente performance.</p>
      </div>
    </div>
    <div class="media">
      <div class="media-left">
        <a href="#"> <img class="media-object" src="${pageContext.request.contextPath}/resources/image/clock-icon-sm.jpg" alt="logo" height="64" width="64">
        </a>
      </div>
      <div class="media-body">
        <h4 class="media-heading">Supervision en temps réel</h4>
        <p class="hidden-xs">Nous supervisons pas seulement des capteurs, mais l'ensemble du système IoT en temps réel. Avec système de diagnostic feu SMART-P3, pompier peut prendre une décision
          rapide lors de l'incendie. App Android et le site Web sont disponibles.</p>
      </div>
    </div>
    <div class="media">
      <div class="media-left">
        <a href="#"> <img class="media-object" src="${pageContext.request.contextPath}/resources/image/calculator-icon-sm.jpg" alt="logo" height="64" width="64">
        </a>
      </div>
      <div class="media-body">
        <h4 class="media-heading">Cloud Computing et la prévision</h4>
        <p class="hidden-xs">Nous utilisons les modèles mathématiques différentes pour construire un modèle de prédiction fiable pour apprendre la tendance de l'incendie.</p>
      </div>
    </div>
  </div>
  <%
    if (session.getAttribute("user") == null) {
  %>
  <div class="col-md-3 col-md-offset-1 col-sm-4 col-sm-offset-0 col-xs-8 col-xs-offset-2">
    <div class="row">
      <div class="col-xs-12" style="margin-bottom: 30px;">
        <ul class="nav nav-tabs">
          <li id="li-signin" role="presentation" class="active"><a id="signin" href="#signin">Log in</a></li>
          <!-- <li id="li-signup" role="presentation"><a id="signup" href="#signup">Sign up</a></li> -->
        </ul>
      </div>
      <form id="target" class="col-xs-12" action="${pageContext.request.contextPath}/auth/signin" method="post">
        <p id="login-message" class="hidden">This sign-up function is not available for the moment</p>
        <div id="div-email" class="form-group">
          <input id="login-email" name="login-email" class="form-control" type="email" placeholder="Email">
        </div>
        <div id="div-password" class="form-group">
          <input id="login-password" name="login-password" class="form-control" type="password" placeholder="Mot de passe">
        </div>
        <div id="div-password2" class="form-group hidden">
          <input id="login-password2" name="login-password2" class="form-control" type="password" placeholder="Confirmer mot de passe">
        </div>
        <button id="submit" class="btn btn-success pull-right" type="submit">OK</button>
      </form>
    </div>
  </div>
  <%
    } else {
  %>
  <div class="col-md-3 col-md-offset-1 col-sm-4 col-sm-offset-0 col-xs-8 col-xs-offset-2">
    <img class="img-responsive hidden-xs" src="${pageContext.request.contextPath}/resources/image/welcome.jpg" alt="logo">
  </div>
  <%
    }
  %>
</div>

<%-- footer --%>
<jsp:include page="/WEB-INF/utils/footer.jsp" />
