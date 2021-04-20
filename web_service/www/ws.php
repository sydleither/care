<?php

require_once(__DIR__."/../controllers/ControllerRequest.php");
require_once(__DIR__."/../controllers/PoliticiansController.php");
require_once(__DIR__."/../controllers/TweetsController.php");

$method = strtolower($_SERVER["REQUEST_METHOD"]);

$path = explode("/",$_SERVER["PATH_INFO"]);
$resource = $path[1];
$controller = ucfirst($resource)."Controller";

if(method_exists($controller,$method)) {
  header("Content-Type: application/json");
  $data = json_decode(file_get_contents("php://input"));
  $request = new ControllerRequest();
  if($method == "get") {
    $request->id = $path[2];
    $request->param = $path[3];
  }
  $response = call_user_func(array($controller,$method), $request);
  echo json_encode($response);
}
else {
  http_response_code(405);
}
