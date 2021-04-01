<?php
require_once(__DIR__."/ControllerRequest.php");
require_once(__DIR__."/ControllerResponse.php");
require_once(__DIR__."/../model/PoliticianModel.php");
require_once(__DIR__."/../model/types/Politician.php");

class PoliticiansController{
  static public function post(ControllerRequest $request) : ControllerResponse{
    $politician = new Politician($request->data);
    $success = PoliticianModel::savePolitician($politician);
    if($success){
      return new ControllerResponse();
    }
    else{
      return new ControllerResponse(null,1);
    }
  }

  static public function get(ControllerRequest $request) : ControllerResponse{
    $politicians = PoliticianModel::getPoliticians();
    return new ControllerResponse($politicians);
  }

  static public function put(ControllerRequest $request) : ControllerResponse{
    $politician = new Politician($request->data);
    PoliticianModel::updatePolitician($politician);
    return new ControllerResponse($politician);
  }
}
?>
