<?php
require_once(__DIR__ . "/Database.php");

class PoliticianModel {
  public static function savePolitician(Politician $politician) : bool {
    $sql = "INSERT into tblPoliticians (name, type, state, party, twitter) VALUES (?, ?, ?, ?, ?)";
    Database::executeSql($sql, "sssss", array($name->name, $type->type, $state->state, $party->party, $twitter->twitter));
    return ! isset(Database::$lastError);
  }
  
  public static function getPoliticians() : array {
    $sql = "SELECT * FROM tblPoliticians";
    return Database::executeSql($sql);
  }
}
?>
