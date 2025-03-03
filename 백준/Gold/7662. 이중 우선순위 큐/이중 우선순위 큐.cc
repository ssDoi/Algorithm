#include <bits/stdc++.h>
#include <set>
#include <iostream>
using namespace std;

int main()
{

	int t;
	cin >> t;
	for (int i = 0; i < t; i++)
	{
		multiset<int> q;
		int k;
		cin >> k;
		for (int o = 0; o < k; o++)
		{
			char order;
			int op;
			cin >> order >> op;
			if (order == 'I')
			{
				q.insert(op);
			}
			else if (order == 'D')
			{
				if (q.empty()) continue;

				if (op == -1)
				{
					auto it = q.begin();
					q.erase(it);
				}
				else
				{
					auto it = --q.end();
					q.erase(it);
				}
			}
		}
		if (q.empty())
		{
			cout << "EMPTY" << endl;
		}
		else
		{
			cout << *(--q.end()) << " " << *q.begin() << endl;
		}

	}

}
