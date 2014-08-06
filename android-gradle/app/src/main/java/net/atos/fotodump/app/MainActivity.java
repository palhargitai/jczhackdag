package net.atos.fotodump.app;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        protected Button button;
        protected ImageView image;
        protected TextView field;
        protected String path;
        protected boolean taken;

        static final int REQUEST_IMAGE_CAPTURE = 0;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            image = (ImageView) rootView.findViewById(R.id.foto_image);
            field = (TextView) rootView.findViewById(R.id.foto_info_text);
            button = (Button) rootView.findViewById(R.id.foto_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startCameraActivity();
                }
            });

            return rootView;
        }

        protected void startCameraActivity() {
            final Intent takePictureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            Log.i("MakeMachine", "resultCode: " + resultCode);
            switch (resultCode) {
                case REQUEST_IMAGE_CAPTURE:
                    Log.i("MakeMachine", "User cancelled");
                    break;

                case RESULT_OK:
                    onPhotoTaken((Bitmap) data.getExtras().get("data"));
                    break;
            }
        }

        protected void onPhotoTaken(final Bitmap imageBitmap) {
            taken = true;

            image.setImageBitmap(imageBitmap);

            field.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
        }
    }
}
