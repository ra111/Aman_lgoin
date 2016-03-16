<?php require_once('config1.php');
if($_SERVER['REQUEST_METHOD']=='POST')
{
$name= $_POST['name'];
$username=  $_POST['username'];
$password= $_POST['password'];

echo "NAME:".$name;
echo "<br>USERNAME:".$username;
echo "<br>PASSWORD:".$password;

$sql="INSERT INTO `loginpass`(`name`, `username`, `password`) VALUES ('$name','$username','$password')";
 
if(mysqli_query($db,$sql))
{
 echo '<br>Class cancelled';
 }
else
{
 echo '<br>Could Not cancel class';
 }
 
 //Closing the database 
 mysqli_close($db);
 }
 