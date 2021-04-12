<?php

require_once(__DIR__ . "/Base.php");

class Politician extends Base {
  public $politicianId, $name, $type, $state, $party, $twitter;

  public function __construct($sourceObject) {
    parent::__construct($sourceObject);
  }
}
