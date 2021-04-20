<?php
require_once(__DIR__ . "/Database.php");

class PoliticianModel {
  public static function getPoliticians() : array {
    $sql = "SELECT * FROM tblPoliticians";
    return Database::executeSql($sql);
  }
}
?>
