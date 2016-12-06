<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- header --%>
<jsp:include page="/WEB-INF/utils/header.jsp" />
<%-- set project name for external js --%>
<script>var projectName = "<%=request.getContextPath()%>";
</script>
<%-- content --%>
<div class="page-header">
  <h1 id="title">Nouveaux appareils</h1>
</div>
<div class="row">
  <div class="col-md-5">
    <h2 id="node-title">Ajout d'un nouveau nœud</h2>
    <hr>
    <div class="row">
      <form method="post" action="<%=request.getContextPath()%>/wsn/addNode" class="form-horizontal">
        <div class="form-group">
          <label for="node-nid" class="col-xs-4 control-label text-left">Nœud ID :</label>
          <div class="col-xs-7">
            <input type="text" class="form-control input-sm" id="node-nid" name="node-nid" placeholder="Ex : ESIG_N_999">
          </div>
        </div>
        <div class="form-group">
          <label for="node-type" class="col-xs-4 control-label text-left">Type :</label>
          <div class="col-xs-7">
            <input type="text" class="form-control input-sm" id="node-type" name="node-type" placeholder="Ex : Zigbee">
          </div>
        </div>
        <div class="form-group">
          <label for="node-bid" class="col-xs-4 control-label text-left">Location :</label>
          <div class="col-xs-7">
            <input type="text" class="form-control input-sm" id="node-bid" name="node-bid" placeholder="Ex : Esigelec">
          </div>
        </div>
        <div class="form-group">
          <label for="node-bid-gps" class="col-xs-4 control-label text-left">GPS :</label>
          <div class="col-xs-4">
            <input type="number" class="form-control input-sm" id="node-longitude" name="node-longitude" placeholder="Ex : 49.3836804" step="0.0000001">
          </div>
          <div class="col-xs-4">
            <input type="number" class="form-control input-sm" id="node-latitude" name="node-latitude" placeholder="Ex : 1.0755375" step="0.0000001">
          </div>
        </div>
        <div class="form-group">
          <label for="node-fid" class="col-xs-4 control-label text-left">Etage :</label>
          <div class="col-xs-7">
            <input type="text" class="form-control input-sm" id="node-fid" name="node-fid" placeholder="Ex : 1">
          </div>
        </div>
        <div class="form-group">
          <label for="node-rid" class="col-xs-4 control-label text-left">Salle :</label>
          <div class="col-xs-7">
            <input type="text" class="form-control input-sm" id="node-rid" name="node-rid" placeholder="Ex : D1 281">
          </div>
        </div>
        <div class="col-xs-offset-3 col-xs-6">
          <button type="submit" class="btn btn-primary btn-md btn-block">Confirmer</button>
        </div>
      </form>
    </div>
  </div>
  <div class="col-md-5 col-md-offset-1">
    <h2 id="sensor-title">Ajout d'un nouveau capteur</h2>
    <hr>
    <div class="row">
      <form method="post" action="<%=request.getContextPath()%>/wsn/addSensor" class="form-horizontal">
        <div class="form-group">
          <label for="sensor-sid" class="col-xs-4 control-label text-left">Capteur ID :</label>
          <div class="col-xs-7">
            <input type="text" class="form-control input-sm" id="sensor-sid" name="sensor-sid" placeholder="Ex : ESIG_TEMP_001">
          </div>
        </div>
        <div class="form-group">
          <label for="sensor-bid" class="col-xs-4 control-label text-left">Bâtiment :</label>
          <div class="col-xs-7">
            <input type="text" class="form-control input-sm" id="sensor-bid" name="sensor-bid" placeholder="Ex : Building A">
          </div>
        </div>
        <div class="form-group">
          <label for="sensor-fid" class="col-xs-4 control-label text-left">Etage :</label>
          <div class="col-xs-7">
            <input type="number" class="form-control input-sm" id="sensor-fid" name="sensor-fid" placeholder="Ex : 1">
          </div>
        </div>
        <div class="form-group">
          <label for="sensor-rid" class="col-xs-4 control-label text-left">Salle :</label>
          <div class="col-xs-7">
            <input type="text" class="form-control input-sm" id="sensor-rid" name="sensor-rid" placeholder="Ex : B1 255">
          </div>
        </div>
        <div class="form-group">
          <label for="sensor-type" class="col-xs-4 control-label text-left">Type :</label>
          <div class="col-xs-7">
            <select name="sensor-type" id="sensor-type" class="form-control input-sm">
              <optgroup label="Others">
                <option value="Temperature">Température</option>
                <option value="Smoke">Fumée</option>
              </optgroup>
              <optgroup label="Gaz">
                <option value="CO2">CO2</option>
                <option value="CO">CO</option>
              </optgroup>
            </select>
          </div>
        </div>
        <div class="form-group">
          <label for="sensor-description" class="col-xs-4 control-label text-left">Description :</label>
          <div class="col-xs-7">
            <textarea class="form-control" rows="3" id="sensor-description" name="sensor-description" placeholder="Ex : Libelium Temperature sensor LIBE9025"></textarea>
          </div>
        </div>
        <div class="col-xs-offset-3 col-xs-6">
          <button type="submit" class="btn btn-primary btn-md btn-block">Confirmer</button>
        </div>
      </form>
    </div>
  </div>
</div>
<%-- footer --%>
<jsp:include page="/WEB-INF/utils/footer.jsp" />
