from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream
import tweepy
import json
import pandas

#Variables that contains the user credentials to access Twitter API 
access_token = "1177396435668611072-ryqf36Gv337fJOIzByI6G8HuaVdcor"
access_token_secret = "JTJGPqwFj382b4A66kial1vkwSkNNVnLEVBKSwtGNwt1s"
consumer_key = "5LF8rYsPuHSTCZEip9JVL2rDS"
consumer_secret = "wmLy9swUbaIhoaUm6DX30ih96yIOsd3Kyw06U2bFlg9kwp2xKY"
tweet_dict = {};

#This is a basic listener that just prints received tweets to stdout.
class StdOutListener(StreamListener):
 
     def on_data(self, data):
          print(data)
          return True

     def tweet_to_json(data):
      tweet_dict = {
        "text": data.text,
        "author_name": data.user.screen_name
    }
     with open('tweet.json', 'w+') as f:
         json.dump(tweet_dict, f) 
      
 
     def on_error(self, status):
         print(status)
 
if __name__ == '__main__':
    #This handles Twitter authentication and the connection to Twitter Streaming API
     l = StdOutListener()
     auth = OAuthHandler(consumer_key, consumer_secret)
     auth.set_access_token(access_token, access_token_secret)
     stream = Stream(auth, l)
     api = tweepy.API(auth,wait_on_rate_limit=True) 
     #This line filter Twitter Streams to capture data by the keywords: 'air pollution'
#      stream.filter(track=['air pollution'], is_async=True)
     
     search_words = "#wildfires"
     #for remove retweets
     #new_search = search_words + " -filter:retweets"
     # for fetch location of user
     
     #date_since = "2014-11-16"
     tweets = tweepy.Cursor(api.search,
              q=search_words,
              lang="en").items(100000000000000)
     # foreach through all tweets pulled
     data = {}
     for tweet in tweets: 
        #users_locs = [[tweet.user.screen_name, tweet.user.location] for tweet in tweets]
        #print(users_locs)
        tweet = ((tweet.text).encode('utf8'));
        data = ({
         'tweet' : tweet 
         })
        print(data)