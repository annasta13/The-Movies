//
//  TabBarButton.swift
//  iosApp
//
//  Created by Annas Surdyanto on 29/11/21.
//  Copyright © 2021 private project. All rights reserved.
//

import SwiftUI

struct Tab {
    var title: String
}

struct TabBarButton: View {
    var fixed = true
    var tabs: [Tab]
    var geoWidth: CGFloat
    @Binding var selectedTab: Int
    
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            ScrollViewReader { proxy in
                VStack(spacing: 0) {
//                    HStack{
//                        Text("The Movies").foregroundColor(Color.white).font(Font.system(size: 18, weight: .semibold))
//                            .padding(.horizontal)
//                        Spacer()
//                    }
                    HStack(spacing: 0) {
                        ForEach(0 ..< tabs.count, id: \.self) { row in
                            Button(action: {
                                withAnimation {
                                    selectedTab = row
                                }
                            }, label: {
                                VStack(spacing: 0) {
                                    Text(tabs[row].title)
                                        .font(Font.system(size: 18, weight: .semibold))
                                        .foregroundColor(Color.white)
                                        .padding(EdgeInsets(top: 10, leading: 3, bottom: 10, trailing: 15))
                                    
                                        .frame(width: fixed ? (geoWidth / CGFloat(tabs.count)) : .none, height: 52)
                                    // Bar Indicator
                                    Rectangle().fill(selectedTab == row ? Color.white : Color.clear)
                                        .frame(height: 3)
                                }.fixedSize()
                            })
                                .accentColor(Color.white)
                                .buttonStyle(PlainButtonStyle())
                        }
                    }
                    .onChange(of: selectedTab) { target in
                        withAnimation {
                            proxy.scrollTo(target)
                        }
                    }
                    .background(Color.black)
                }
            }
        }
        .frame(height: 55)
        .onAppear(perform: {
            //UIScrollView.appearance().backgroundColor = UIColor(.white)
            UIScrollView.appearance().bounces = fixed ? false : true
        })
        .onDisappear(perform: {
            UIScrollView.appearance().bounces = true
        })
    }
}
