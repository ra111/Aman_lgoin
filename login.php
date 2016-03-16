<?php require_once('config1.php');
if($_SERVER['REQUEST_METHOD']=='POST')
{

$username=  $_POST['username'];
$password= $_POST['password'];


echo "<br>USERNAME:".$username;
echo "<br>PASSWORD:".$password;

$sql="SELECT * FROM `loginpass` where  username='$username' and password='$password'";

 $result=mysqli_query($db,$sql);

if ($result->num_rows > 0) {
    
        $response["success"] = 1;
      
 
} else {
     $response["success"] = 0;
}
		}
	else {
		die("Query failed");
		 $response["success"] = 2;
	}
	echo json_encode($response);
 mysqli_close($db);
?>

 