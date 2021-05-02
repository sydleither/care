<?php
require_once(__DIR__ . "/Database.php");

class TweetModel {
  public static function getTweets(string $twitter) : array {
    $sql = "SELECT * FROM tblTweets WHERE twitter=?";
    return Database::executeSql($sql, "s", array($twitter));
  }

  public static function saveTweets(Tweet $tweet) : bool {
    $sql = "INSERT INTO tblTweets (twitter, date, link, text) VALUES (?, ?, ?, ?)";
    Database::executeSql($sql, "ssss", array($tweet->twitter, $tweet->date, $tweet->link, $tweet->text));
    return ! isset(Database::$lastError);
  }

  public static function newestDate() : array {
    $sql = "SELECT MAX(date) FROM tblTweets";
    return Database::executeSql($sql);
  }
}
?>
