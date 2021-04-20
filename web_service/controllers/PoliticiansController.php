<?php
require_once(__DIR__."/ControllerRequest.php");
require_once(__DIR__."/ControllerResponse.php");
require_once(__DIR__."/../model/PoliticianModel.php");
require_once(__DIR__."/../model/types/Politician.php");

class PoliticiansController {
  static public function get(ControllerRequest $request) : ControllerResponse {
    $politicians = PoliticianModel::getPoliticians();
    return new ControllerResponse($politicians);
  }
}
?>
