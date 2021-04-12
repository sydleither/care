<?php
require_once(__DIR__ . "/Database.php");

class PoliticianModel {
  public static function savePolitician(Politician $politician) : bool {
    $sql = "INSERT INTO tblPoliticians (name, type, state, party, twitter) VALUES (?, ?, ?, ?, ?)";
    Database::executeSql($sql, "sssss", array($politician->name, $politician->type, $politician->state, $politician->party, $politician->twitter));
    return ! isset(Database::$lastError);
  }
  
  public static function getPoliticians() : array {
    $sql = "SELECT * FROM tblPoliticians";
    return Database::executeSql($sql);
  }
}
?>
