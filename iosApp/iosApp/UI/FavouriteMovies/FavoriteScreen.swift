//
//  FavoriteScreen.swift
//  iosApp
//
//  Created by Developer on 30/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct FavoriteScreen: View {
    @ObservedObject private var viewModel = FavouriteViewModel()
    private let refreshName = "favoriteMovies"
    
    //    init(){
    //            UINavigationBar.appearance().backgroundColor = UIColor(Color.black)
    //            UINavigationBar.appearance().titleTextAttributes = [.foregroundColor: UIColor.white]
    //            UINavigationBar.appearance().isTranslucent = false
    //
    //    }
    
    var body: some View {
        let state = viewModel.state
        //NavigationView{
        ScrollView(.vertical){
            RefreshView(coordinateSpaceName: refreshName){viewModel.initState()}
            
            if (!state.movieResponse.isEmpty){
                Spacer().padding(.top, 40)
                MovieScreenContent(movieList: state.movieResponse)
            }
            else {
                VStack{
                    Text("No favourite movie yet")
                }.frame(height: UIScreen.main.bounds.height)
            }
        }.coordinateSpace(name: refreshName)
            .onAppear(perform: {
                UIScrollView.appearance().backgroundColor = UIColor(.white)
                viewModel.initState()
            })
            .navigationTitle("Favourite Movie")
            .navigationBarTitleDisplayMode(.inline)
        
    }
}

struct FavoriteScreen_Previews: PreviewProvider {
    static var previews: some View {
        FavoriteScreen()
    }
}
