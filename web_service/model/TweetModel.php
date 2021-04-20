<?php
require_once(__DIR__ . "/Database.php");

class TweetModel {
  public static function getTweets(string $twitter) : array {
    $sql = "SELECT * FROM tblTweets WHERE twitter=?";
    return Database::executeSql($sql, "s", array($twitter));
  }
}
?>
