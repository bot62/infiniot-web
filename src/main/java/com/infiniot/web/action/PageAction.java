package com.infiniot.web.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.infiniot.web.model.Node;
import com.infiniot.web.service.WSNService;
import com.opensymphony.xwork2.ActionSupport;

public class PageAction extends ActionSupport implements RequestAware {

  private static final long serialVersionUID = 3672324106737195003L;
  private WSNService wsnService;
  private Map<String, Object> params;

  public WSNService getWsnService() {
    return wsnService;
  }

  public void setWsnService(WSNService wsnService) {
    this.wsnService = wsnService;
  }

  /**
   * Load page: /node
   * 
   * @return invoked result
   */
  public String node() {
    List<Node> nodes = wsnService.getNodes();
    params.put("nodes", nodes);
    return "node";
  }

  /**
   * Load page: /admin
   * 
   * @return invoked result
   */
  public String admin() {
    int mode = wsnService.getRunningMode();
    long runtime = wsnService.runningSince();
    params.put("mode", mode);
    params.put("runtime", runtime);
    return SUCCESS;
  }

  @Override
  public void setRequest(Map<String, Object> params) {
    this.params = params;
  }

}
