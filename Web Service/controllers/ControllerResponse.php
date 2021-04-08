<?php
class ControllerResponse{
	public $data, $status, $error;
	public function __construct($data=null, $status=0, $error=null){
		$this->data = $data;
		$this->status = $status;
		$this->error = $error;
	}
}
?>
