<?php

require_once(__DIR__ . "/Base.php");

class Tweet extends Base {
  public $tweetId, $twitter, $date, $link, $text;

  public function __construct($sourceObject) {
    parent::__construct($sourceObject);
  }
}
