<?php
require_once(__DIR__."/ControllerRequest.php");
require_once(__DIR__."/ControllerResponse.php");
require_once(__DIR__."/../model/TweetModel.php");
require_once(__DIR__."/../model/types/Tweet.php");

class TweetsController {
  static public function get(ControllerRequest $request) : ControllerResponse {
    $tweets = TweetModel::getTweets($request->id);
    return new ControllerResponse($tweets);
  }
}
?>
