<?php
require_once(__DIR__."/ControllerRequest.php");
require_once(__DIR__."/ControllerResponse.php");
require_once(__DIR__."/../model/TweetModel.php");
require_once(__DIR__."/../model/types/Tweet.php");

class TweetsController {
  static public function get(ControllerRequest $request) : ControllerResponse {
    $queryString = array();
    parse_str($_SERVER["QUERY_STRING"], $queryString);
    
    if($queryString["query"] == "newest_date") {
      $tweets = TweetModel::newestDate();
    }
    else {
      $tweets = TweetModel::getTweets($request->id);
    }  
    return new ControllerResponse($tweets);
  }

  static public function post(ControllerRequest $request) : ControllerResponse {
    foreach($request->data as $tweetTemp) {
      $tweet = new Tweet($tweetTemp);
      $success = TweetModel::saveTweet($tweet);
    }
    if($success) {
      return new ControllerResponse();
    }
    else {
      return new ControllerResponse(null, 1);
    }
  }
}
?>
