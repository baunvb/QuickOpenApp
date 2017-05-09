package com.baunvb.quickopenapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baunvb.quickopenapp.model.Weather;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUES_CODE = 100;
    private Button btDialer, btMesseaging, btContacts, btCamera, btMusic, btGallery;
    private ImageView imgCondIcon;
    private TextView tvTemp, tvHumidity, tvCondDescr, tvCity, tvWind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btCamera = (Button) findViewById(R.id.bt_camera);
        btContacts = (Button) findViewById(R.id.bt_contacts);
        btDialer = (Button) findViewById(R.id.bt_dialer);
        btGallery = (Button) findViewById(R.id.bt_gallery);
        btMusic = (Button) findViewById(R.id.bt_music);
        btMesseaging = (Button) findViewById(R.id.bt_messeaging);

        btCamera.setOnClickListener(this);
        btContacts.setOnClickListener(this);
        btDialer.setOnClickListener(this);
        btGallery.setOnClickListener(this);
        btMusic.setOnClickListener(this);
        btMesseaging.setOnClickListener(this);

        imgCondIcon = (ImageView)findViewById(R.id.condIcon);
        tvCondDescr = (TextView) findViewById(R.id.condDescr);
        tvCity = (TextView) findViewById(R.id.cityText);
        tvTemp = (TextView) findViewById(R.id.temp);
        tvHumidity = (TextView) findViewById(R.id.humidity);
        tvWind = (TextView) findViewById(R.id.wind);

        String city = "Ha Noi";
        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUES_CODE && resultCode == RESULT_OK){
            Uri uri = data.getData();
            Intent intent = new Intent(this, ImageViewActivity.class);
            intent.setData(uri);
            startActivity(intent);
        }
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));

            try {
                weather = JSONWeatherParser.getWeather(data);

                // Let's retrieve the icon
                weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);
            if (weather.iconData != null && weather.iconData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                imgCondIcon.setImageBitmap(img);
            } else {
            }

            tvCity.setText(weather.location.getCity() + "," + weather.location.getCountry());
            tvCondDescr.setText(weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescr() + ")");
            tvTemp.setText("" + Math.round((weather.temperature.getTemp() - 273.15)) + "°С ");
            tvHumidity.setText("" +weather.currentCondition.getHumidity() + "%");
            tvWind.setText(""+weather.wind.getSpeed()+ " m/s");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_camera:
                Intent intentCamera = new Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA);
                startActivity(intentCamera);
                break;
            case R.id.bt_contacts:
                Intent intentContact = new Intent();
                intentContact.setAction(Intent.ACTION_MAIN);
                intentContact.addCategory(Intent.CATEGORY_APP_CONTACTS);
                startActivity(intentContact);
                break;
            case R.id.bt_dialer:
                Intent intenDialer = new Intent(this, CallActivity.class);
                startActivity(intenDialer);
                break;
            case R.id.bt_gallery:
                Intent intentGallery = new Intent();
                intentGallery.setAction(Intent.ACTION_PICK);
                intentGallery.setType("image/*");
                startActivityForResult(Intent.createChooser(intentGallery, "Chose image"), REQUES_CODE);
                break;

            case R.id.bt_messeaging:
                Intent intentMessager = new Intent();
                intentMessager.setAction(Intent.ACTION_MAIN);
                intentMessager.addCategory(Intent.CATEGORY_APP_MESSAGING);
                startActivity(intentMessager);
                break;
            case R.id.bt_music:
                Intent intentMusic = new Intent();
                intentMusic.setAction(Intent.ACTION_MAIN);
                intentMusic.addCategory(Intent.CATEGORY_APP_MUSIC);
                startActivity(intentMusic);
                break;
            default:
                break;
        }
    }
}
