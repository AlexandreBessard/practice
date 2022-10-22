https://www.javatpoint.com/association-in-java

AGGREGATION:
In Java, the Aggregation association defines the HAS-A relationship. 
Aggregation follows the one-to-one or one-way relationship. 
If two entities are in the aggregation composition, and one entity fails due to some error, 
it will not affect the other entity.

Let's take the example of a toy and its battery. The battery belongs to a toy, 
and if the toy breaks and deletes from our database, the battery will still 
remaining in our database, and it may still be working. 
So in Aggregation, objects always have their own lifecycles when the ownership exists there.