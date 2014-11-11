/*
SQLyog Community v9.20 
MySQL - 5.5.30 : Database - nlp
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nlp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `nlp`;

/*Table structure for table `aspectsentiment` */

DROP TABLE IF EXISTS `aspectsentiment`;

CREATE TABLE `aspectsentiment` (
  `sentenceid` int(11) NOT NULL,
  `aspect` varchar(100) NOT NULL,
  `sentiment` varchar(100) NOT NULL,
  `sentimentvalue` double DEFAULT '0',
  PRIMARY KEY (`sentenceid`,`aspect`,`sentiment`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `aspectsentiment` */

/*Table structure for table `reviewmaster` */

DROP TABLE IF EXISTS `reviewmaster`;

CREATE TABLE `reviewmaster` (
  `RATINGS` int(11) DEFAULT NULL,
  `REVIEWCOMMENTS` varchar(1014) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `GENDER` varchar(1) DEFAULT NULL,
  `NAME` varchar(4) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `reviewmaster` */

insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'The JW mariott in Pune seems midway between a courtyard mariott and a JW one. Its mainly a business hotel and the rest of the inhabitants are mostly here on work. The room is comfortable and not the largest.. the breakfast spread is very good and the bakery is a fun one. Its a very comfortable stay without being very very impressive.','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'Centrally located in between Pune Airport and Mumbai Pune Expressway, JW Marriott Hotel Pune features luxury amenities, integrated eco-friendly enhancements, and is awarded with LEED Gold certification. For guests traveling on business, five-star amenities at our luxurious JW hotel include wireless connectivity, large desks with ergonomic chairs, in-room vaults for laptops, and fresh breakfast options. With nearly 40,000 square feet of sophisticated meeting space, events at JW Marriott Pune can comfortably accommodate up to 2,000 guests. Our luxury JW Marriott offers access to many tourist attractions, including historic forts, museums, shopping districts and hill stations. Guests of our exquisite lodging destination can also enjoy an array of savory, in-house dining options, including international fare, Italian cuisine, specialty vegetarian Asian options, and North-Indian delicacies. Discover extraordinary style and service at JW Marriott Hotel Pune, one of the most pristine hotels in Pune, India.','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'We stayed here for 3 days over Christmas weekend. This was for the 25th marriage Anniversary of our relatives Mr. Suhas and Vinita Mantri. We had a rollicking time for those 3 days. The food and service was very good 					 Stayed December 2013, travelled with family','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'Bar101 is amazing. Their collection of alcohol is huge, and the staff is very warm and friendly. The best place to have a smoke. Go there, order a Cohiba Cigar with your girl, and feel like a King. Wonderful service, comfortable room with blazing fast internet. A true delight indeed.','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'Overall quite disappointing with regards to interiors of the hotel, quality of room etc. Felt like old run down rooms even though I understand it\'s relatively new. Food was ok, not exceptional. Location I guess is good but hardly ventured out as the functions were in the hotel itself.','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'Excellent property it is with wonderful rooms, food, spa.... Recently spent 3 nights here. Got upgraded to a suite on 19th floor. Beautiful room with superb views and awesome bathroom. The spa is amongst the best I gave seen ever. The only thing lousy about thus place is the main entrance chaos caused by the number of cars coming and going. At times had to wait more than 20 mins for my car to be brought.','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'I have stayed for 3 nights on 14th floor.The rooms are very spacious and has all necessary amenities. It is located in the prime and posh area of city. The lobby seems congested and coffee room is also congested. The staff is very hospitable. I attended dinner meeting on first floor meeting room.It has an excellent board room and served good food and services. The breakfast served lacked variety and served on first floor.','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'We recently attended some prominent Industrialist\'s Wedding at this property. And we are aware of the exorbitant charges as we are very close friends of Grooms parents. Having stayed in similar properties all our lives we have a certain level of expectation and I am sorry to state that this hotel let us down on several occasions.  We were shocked to note that the towels were cheap and tainted and hardly what any hotel would like to claim ownership to. On complaining they were replaced by regular white fluffy ones, only to be replaced by the other variety a day later. House keeping levels were generally poor and when we complained no one actually seemed to care','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'A heavenly place and a experience worth remembering. the Rooms were absolutely clean and tidy, could not find a spec of dust . the rooms were soundless it felt as if you are somewhere far away from the hustle bustle of the city. The Service was par excellence and the staff very helpful and courteous. somewhat the best I have seen in India.','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'i travelled for a conference but took my family along and stayed a couple on nights at the property. the hotel is good overall ... with a good location, good food and good care. but... could do with more things to do for the kids, slightly faster service and an improved spa service. To be fair to them ... i had a bad service experience in the Spa ... however the Director of spa, Mahima Trivedi, took prompt action on my complaint and even compensated with a refund for which i thank her.... but i hope she gets things right and keeps the promise she made me. So ... like i said ... some good ... some bad.... but nothing that cant be changed for the better!','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'We were worried that hosting my daughter\'s engagement party at this location would be too expensive, will not have good food, parking will be a problem and service will be snobbish. We were pleasantly surprised that all our fears turned into memorable things about our event. Very cost-effective, excellent and plenty of food, ample parking and outstanding service - all 200 guests were personally taken care of.   First Devika James then Jay Chhajalani planned the whole event really well for us.   The only suggestion I would have is that the hosts should be involved right from the beginning to discuss all details, so you know exacty what to expect.If you know what you exactly want, they will deliver!  And, by the way, for any event, for decorations find Kedar Navale - he does a fantastic job!','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'The Marriot hotel is just a piece of excellency the engineers have done a heavy hard word a bring up a beautiful hotel.thee service is excellent, no words for the cleanliness and talk about food i think spice kitchen is the best one among all the restaurants.The rooms are wonderful and are cleaned often which is a good sign and no more words i have to describes this fabulous piece of art.','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'I had lunch a couple of times as part of some company meetings at this place. The feel when you enter the hotel is of belonging to a different class altogether. The restaurants are very good especially the one at the ground floor. The buffet spread is exhaustive and caters very well for vegetarians and non-vegetarians.  During PIL season, we can even catch a glimpse of the PIL cricketers since most of them are hosted here.','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'Visited this hotel while in Pune for business. Great rooms, excellent service, but the highlight for me was the dining options on the top floor. This is a beautiful setting with great views over Pune city. The rooftop outdoor area is magnificent and has a fab bar with great service. The food was modern Indian. Very good options. All tried were wonderful.','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'Beautiful JW Marriott in Pune has long been one of my favorite hotels. It\'s the perfect place to splurge on rare treats, spend an unforgettably romantic time, and just enjoy (for a few days) a breathtakingly lovely world of gleaming polished floors, colorful flowers (both real and captured in Chihuly glass) and enchanting art.  JW Marriott Pune showcases them well, offering multiple places to sip, mingle, and enjoy the views. The Brunch at Spice Kitchen is one of my favorites, along with evening cocktails at Pashaa.  Poolside have been made even more tempting with the addition of a new, exclusive cocktail menu.  I highly recommend this hotel for all the experience u need.','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'This Marriott property in Pune is amongst the best, more so considering service, value for money and competition in region.  Fairly new, clean rooms, comfortable beds, amazing breakfast, great spa, several restaurants and bars....and fantastic location. There is little that can go wrong if you choose this. I have thoroughly enjoyed my last 3 week long stays and look forward to several more...','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'This was my first stay at JW Marriot in Pune and I can describe t in one word \"Awesome\" I loved the place from the moment I entered their premises. And in the morning they have live flute in the lobby which just makes the atmosphere so fresh and calm.. Room: Nice, specious and comfortable room with classy toiletries and high quality comforts. I was on the 9th floor and had a great view of the city. Internet speed was nice as well. Location:  A big plus as this is much closer to Mumbai highway as compare to other 5 stars. It is also near to shopping place, and other popular locations like Deccan, FC Road. Service: A class, no doubt about it. FOOD: A+ again. I liked there breakfast spread and it was very tasty. The lunch was quite good as well.  I used there conference room as well for our client meetings and presentation. They were also of best quality and of good facilities.  Next time I would definitely want to use there pool and spa service','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'Ok, let me confess, I am a Marriott Elite member and hence could get 2 nights in Marriott on my points. The membership also entitled to visit the exclusive club for members. I got a small but brilliant room at the 23rd floor in the heart of Pune (well almost) Brilliant view from the large window, stayed with my wife and kid. The hotel has a brilliant Spa, probably the best of the city, nice restaurants. Had 2 relaxing days there.  The only grouse was my kid wouldnt fit into any of the new year parties and hence I had spend it in the room with family, no complaints though. Courteous helpful smiling staff all around makes you feel brilliant. The breakfast is awesome, amazing variety, as good as it gets.(Though the idlis need improvement)  Would definitely recommend this luxury if you can afford it, or better if you are a corporate :)','2014-01-12','M','John');
insert  into `reviewmaster`(`RATINGS`,`REVIEWCOMMENTS`,`DATE`,`GENDER`,`NAME`) values (4,'This is one of JW Marriott\'s signature properties that is splashy and offers good service. The hotel is centrally located and offers some excellent food choices. The first time I stayed here was rather uneventful and therefore returned for a second time. It was a different experience the second time over. As I walk into the room on a cold winter evening, the thermostat did not work to raise the temperature. The water is the shower was not warm either. The technicians and plumbers were hard at work every day thru my stay, but the issue never got resolved until I left. Apparently, the issue was more systemic than just my room. The staff were very courteous and sincere in their attempt to address the issue. However, the upper management seemed inattentive. They need to stay close and address the basic issues for the guest comfort, otherwise the great brand of JW will go for a toss.','2014-01-12','M','John');

/*Table structure for table `socialinsightsinference` */

DROP TABLE IF EXISTS `socialinsightsinference`;

CREATE TABLE `socialinsightsinference` (
  `propertyid` int(11) NOT NULL,
  `sentimentCategoryId` varchar(100) NOT NULL,
  `nentimentvalueneg` double DEFAULT '0',
  `sentimentvaluepos` double DEFAULT '0',
  `sentimentvalueneutral` double DEFAULT '0',
  PRIMARY KEY (`propertyid`,`sentimentCategoryId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `socialinsightsinference` */

insert  into `socialinsightsinference`(`propertyid`,`sentimentCategoryId`,`nentimentvalueneg`,`sentimentvaluepos`,`sentimentvalueneutral`) values (1,'OVERALL',0.1896551724137931,0.5172413793103449,0.29310344827586204);
insert  into `socialinsightsinference`(`propertyid`,`sentimentCategoryId`,`nentimentvalueneg`,`sentimentvaluepos`,`sentimentvalueneutral`) values (1,'ROOMS',0.35294117647058826,0.29411764705882354,0.35294117647058826);
insert  into `socialinsightsinference`(`propertyid`,`sentimentCategoryId`,`nentimentvalueneg`,`sentimentvaluepos`,`sentimentvalueneutral`) values (1,'FOOD & BEVERAGE',0.38461538461538464,0.5384615384615384,0.07692307692307693);
insert  into `socialinsightsinference`(`propertyid`,`sentimentCategoryId`,`nentimentvalueneg`,`sentimentvaluepos`,`sentimentvalueneutral`) values (1,'GENERAL STAFF',0.07692307692307693,0.6153846153846154,0.3076923076923077);
insert  into `socialinsightsinference`(`propertyid`,`sentimentCategoryId`,`nentimentvalueneg`,`sentimentvaluepos`,`sentimentvalueneutral`) values (1,'GENERAL HOTEL',0.25,0.5625,0.1875);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
