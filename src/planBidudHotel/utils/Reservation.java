package planBidudHotel.utils;

import java.util.Date;

public class Reservation {

	 	private final long reservationID;
	    private long requestID;
	    private long hotelID;
	    private Date startDate;
	    private Date endDate;
		public Reservation(long reservationID, long requestID, Date startDate, Date endDate, long hotelID) {
			super();
			this.reservationID = reservationID;
			this.requestID = requestID;
			this.hotelID = hotelID;
			this.startDate = startDate;
			this.endDate = endDate;
		}
		/**
		 * @return the requestID
		 */
		public long getRequestID() {
			return requestID;
		}
		/**
		 * @param requestID the requestID to set
		 */
		public void setRequestID(long requestID) {
			this.requestID = requestID;
		}
		/**
		 * @return the hotelID
		 */
		public long getHotelID() {
			return hotelID;
		}
		/**
		 * @param hotelID the hotelID to set
		 */
		public void setHotelID(long hotelID) {
			this.hotelID = hotelID;
		}
		/**
		 * @return the startDate
		 */
		public Date getStartDate() {
			return startDate;
		}
		/**
		 * @param startDate the startDate to set
		 */
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		/**
		 * @return the endDate
		 */
		public Date getEndDate() {
			return endDate;
		}
		/**
		 * @param endDate the endDate to set
		 */
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		/**
		 * @return the reservationID
		 */
		public long getReservationID() {
			return reservationID;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (hotelID ^ (hotelID >>> 32));
			result = prime * result + (int) (requestID ^ (requestID >>> 32));
			result = prime * result + (int) (reservationID ^ (reservationID >>> 32));
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Reservation other = (Reservation) obj;
			if (hotelID != other.hotelID)
				return false;
			if (requestID != other.requestID)
				return false;
			if (reservationID != other.reservationID)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Reservation [reservationID=" + reservationID + ", requestID=" + requestID + ", hotelID=" + hotelID
					+ "]";
		}
	    
	    
}
