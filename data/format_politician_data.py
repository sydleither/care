'''
Create CSV to upload into phpMyAdmin
'''

import pandas as pd
import requests
import json

politicians_json = json.loads(requests.get('https://theunitedstates.io/congress-legislators/legislators-current.json').text)
social_media_json = json.loads(requests.get('https://theunitedstates.io/congress-legislators/legislators-social-media.json').text)

formatted_politicians = []
for politician in politicians_json:
    formatted_politicians.append({
        'id':politician['id']['bioguide'],
        'name':politician['name']['official_full'].replace('"', ''),
        'type':politician['terms'][-1]['type'],
        'state':politician['terms'][-1]['state'],
        'party':politician['terms'][-1]['party']
    })

formatted_social_media = []
for politician in social_media_json:
    try:
        formatted_social_media.append({
            'id':politician['id']['bioguide'],
            'twitter':politician['social']['twitter']
        })
    except:
        formatted_social_media.append({
            'id':politician['id']['bioguide'],
            'twitter':'N/A'
        })

politician_df = pd.DataFrame.from_dict(formatted_politicians, orient='columns')
social_media_df = pd.DataFrame.from_dict(formatted_social_media, orient='columns')

df = politician_df.join(social_media_df.set_index('id'), on='id')
df.pop('id')
df.index += 1
df.to_csv('politicians.csv', header=False)