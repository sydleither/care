<?php
class ControllerRequest{
	public $data, $id, $param;
	public function __construct($data=null, int $id=null, string $param=null){
		$this->data = $data;
		$this->id = $id;
		$this->param = $param;
	}
}
?>
