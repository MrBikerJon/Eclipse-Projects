
public class ReverseString {

	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof PlayingCard) {
			PlayingCard anotherCard = (PlayingCard) obj;
			return rank == anotherCard && suit.equals(anotherCard.suit);
		}
		return false;
	}
	
	@Override
	public int hashCode(PlayingCard obj)
	{
		int result = Short.hashCode(rank);
		result = 31 * result + Short.hashCode(suit.length());
		return result;
	}
	
}
	
