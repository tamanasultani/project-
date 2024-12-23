# project-Tamana Sultani
Final Project Report
K- Dimensional Tree project 

The first class is the point class. The purpose of creating this class is to represent a data point in k-d space. This class has two attributes; String name and double array  coordinates that represent name and coordinates of each data point. The other class is K-dNode. The purpose of this class is to represent nodes of the K-d tree. It stores a Point object and has references to its left and right child nodes. This class is  forming the structure of the tree by linking to subtrees.
Kdtree class  creates and manages a k-d tree. It starts with a root node and works with data points in `k` dimensions. This  class can build a balanced tree from a list of points, find the closest point to a given one, calculate the tree’s height, and determine the smallest possible height for a tree with a certain number of points. This class can also calculate distances between points and display the tree’s structure. The last class is the Kdnn class .This class  is the main part of the project  that handles input and output. It reads data points from a file to build the k-d tree, calculates and displays the tree's height and its minimal possible height and accepts test points from the user to find their nearest neighbors. 
 	For tree construction we checked that the tree's height matches the theoretical minimum height. Also, we confirmed its balance by examining the printed structure. Then  verified the median sorting manually. For nearest neighbor searches, we compared the program's results with manually calculated distances and tested edge cases like boundary and overlapping points. The tree's visualization was validated by matching its printed structure with the input dataset and checking the indentation for node depth. We tested edge cases, ensuring the project handles empty datasets, single points, and duplicates without errors. Finally, performance was evaluated with large datasets to ensure the tree construction and searches remain efficient as the data size grows. 
References:
https://stackoverflow.com/questions/253767/kdtree-implementation-in-java

https://youtu.be/irkJ4gczM0I?si=ZkpenucCgMM-d3rV

https://youtu.be/W3XkBhYvNS0?si=TKrePQuA21ypEB0S

https://youtu.be/2Gul_-cbWM0?si=YoHttrrkym1i2v8d

https://youtu.be/3hykKbMmwcc?si=1fps9NNmOHVxblI2
https://stackoverflow.com/questions/14431032/i-want-to-calculate-the-distance-between-two-points-in-java



