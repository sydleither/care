'''
Create CSV to upload into phpMyAdmin
'''

import pandas as pd
import requests
import json
import unidecode

df_politicians = pd.read_csv('politicians.csv', header=None)
politician_twitters = df_politicians[5].tolist()

for year in ['2021']:
    formatted_tweets = []
    for month in range(1,13):
        for day in range(1,32):
            try:
                tweet_json = json.loads(requests.get(
                    f'https://alexlitel.github.io/congresstweets/data/{year}-{str(month).zfill(2)}-{str(day).zfill(2)}.json'
                ).text)
                for tweet in tweet_json:
                    if tweet['screen_name'] in politician_twitters:
                        formatted_tweets.append({
                            'twitter':tweet['screen_name'],
                            'date':tweet['time'][0:10],
                            'link':tweet['link'],
                            'text':unidecode.unidecode(tweet['text'].replace('“','\'').replace('”','\'').replace('"','\'').replace('\\',''))
                        })
            except:
                break
    df = pd.DataFrame.from_dict(formatted_tweets, orient='columns')
    df.index += 1
    df.to_csv(f'tweets_{year}.csv', header=False)
    df.to_csv(f'tweets_{year}.csv.zip', header=False, compression="gzip")
