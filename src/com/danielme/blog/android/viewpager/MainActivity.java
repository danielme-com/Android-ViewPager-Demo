package com.danielme.blog.android.viewpager;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 
 * @author danielme.com
 * 
 */
public class MainActivity extends Activity
{
	private ViewPager viewPager;

	private LinearLayout page1;
	private LinearLayout page2;
	private ListView page3;
	private LinearLayout page4;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new MainPageAdapter());
		viewPager.addOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position)
			{
				Toast.makeText(MainActivity.this, "page " + (position + 1), Toast.LENGTH_SHORT).show();				
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{
						
			}
			
			@Override
			public void onPageScrollStateChanged(int state)
			{
					
			}
		});		

	}

	class MainPageAdapter extends PagerAdapter
	{

		@Override
		public int getCount()
		{
			return 4;
		}

		@Override
		public Object instantiateItem(ViewGroup collection, int position)
		{
			View page = null;
			switch (position)
			{
			case 0:
				if (page1 == null)
				{
					page1 = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.page_one, null);
				}
				page = page1;
				break;
			case 1:
				if (page2 == null)
				{
					page2 = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.page_two, null);
				}
				page = page2;
				break;
			case 2:
				if (page3 == null)
				{
					page3 = (ListView) LayoutInflater.from(MainActivity.this).inflate(R.layout.page_three, null);
					initListView();
				}
				page = page3;
				break;
			default:
				if (page4 == null)
				{
					page4 = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.page_four, null);
				}
				page = page4;
				break;
			}

			((ViewPager) collection).addView(page, 0);

			return page;
		}

		@Override
		public boolean isViewFromObject(View view, Object object)
		{
			return view == object;
		}

		@Override
		public void destroyItem(View collection, int position, Object view)
		{
			((ViewPager) collection).removeView((View) view);
		}

		private void initListView()
		{
			String[] items = new String[50];
			for (int i = 0; i < 50; i++)
			{
				items[i] = "Item " + i;
			}
			page3.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.textview, items));
			page3.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					Toast.makeText(MainActivity.this, (String) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
				}
			});

		}
	}

}