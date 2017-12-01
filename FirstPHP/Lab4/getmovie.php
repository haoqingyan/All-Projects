<!DOCTYPE html>
<!-- Author: Haoqing Yan -->
<html>
<head>
<meta charset="UTF-8">
<title>Mengtao Tang</title>
<link rel="stylesheet" type="text/css" href="mystyle.css">
</head>
<body>

	Complete the PHP code to make your page
	<br> look like the screenshot on the lab spec.
	<br>

<?php
// Read data from input file and establish some local variables
// $movie could be tmnt, tmnt2, mortalkombat, princessbride
$movie = $_GET ["folder"]; // Get the query parameter from the local host url
$imageFileName = $movie . "/overview.png"; // define the path of the overview picture
$info = $movie . "/info.txt"; // define the path of the information txt file
$info_Array = file ( $info ); // read from the file
$movie_Title = str_replace ( "\n", "", $info_Array [0] ); // get the title of the movie
$movie_Year = str_replace ( "\n", "", $info_Array [1] ); // get the year of the movie
$movie_Rating = str_replace ( "\n", "", $info_Array [2] ); // get the rating of the movie

?>

	<div class=movieTitle>
		<?php
		echo $movie_Title;
		?>
	</div>

	<br>

	<div class=movieRating>
		<p><?php
		echo $movie_Rating . '%';
		?>
		</p>
	</div>

	<div class=movieYear>
		<p>
		<?php
		echo "Released in " . $movie_Year;
		?>
		</p>
	</div>

	<div>
		<img class=moviePost src=<?php echo $imageFileName; ?>
			alt="Movie Post">
	</div>


</body>
</html>