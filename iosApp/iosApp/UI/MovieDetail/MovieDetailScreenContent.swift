//
//  MovieDetailScreenContent.swift
//  iosApp
//
//  Created by Developer on 29/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MovieDetailScreenContent: View {
    var data: MovieDetailAndReview
    var onRefresh: () -> Void
    var isFavorited: Bool
    var onFavoriteClicked: () -> Void
    @StateObject private var loader: ImageLoader
    private let refreshName : String = "detailMovie"
    //@State private var image: UIImage = UIImage()
    @State private var reviewIdExpanded: String = ""
    
    init(data: MovieDetailAndReview,
         onRefresh: @escaping ()  -> Void,
         isFavorited: Bool,
         onFavoriteClicked:@escaping () -> Void){
        self.data = data
        self.onRefresh = onRefresh
        self.isFavorited = isFavorited
        self.onFavoriteClicked = onFavoriteClicked
        _loader = StateObject(wrappedValue: ImageLoader(url: data.movieDetail!.poster.asUrl(), cache: Environment(\.imageCache).wrappedValue))
    }
    
    var body: some View {
        let movie = data.movieDetail
        let reviewList = data.review?.reviewData
        let favoriteImage = isFavorited ? "favorited" : "unfavorited"
        if (movie != nil){
            
            ScrollView{
                RefreshView(coordinateSpaceName: self.refreshName, onRefresh: onRefresh)
                VStack{
                    
                    if loader.image != nil {
                        Image(uiImage: loader.image!).resizable().clipped()
                            .aspectRatio(contentMode: .fit)
                            .frame(width: 200, height: 200)
                    } else {
                        Image("default_image").resizable().clipped()
                            .aspectRatio(contentMode: .fit)
                            .frame(width: 200, height: 200)
                    }
                    
                    if (movie!.overview != nil){
                        Text(movie!.overview!).padding(.vertical)
                    }
                    
                    HStack{
                        VStack(alignment: .leading){
                            Text("Release Date: \(movie!.releaseDate)").font(.caption).foregroundColor(.gray)
                            Text("Budget: \(movie!.budget)").font(.caption).foregroundColor(.gray)
                        }
                        Spacer()
                        VStack(alignment: .trailing){
                            Text("Popularity: \(movie!.popularity)").font(.caption).foregroundColor(.gray)
                            Text("Vote Average: \(movie!.voteAverage)").font(.caption).foregroundColor(.gray)
                        }
                    }
                    
                    HStack{
                        Text("Review").bold().padding(.vertical)
                        Spacer()
                    }
                    
                    if (reviewList != nil){
                        ForEach(reviewList!, id: \.id) { review in
                            ZStack{
                                VStack(alignment: .leading){
                                    HStack{
                                        AsyncImage(url: review.authorDetails.avatar.asUrl(),
                                                   placeholder: { Image("default_profile").resizable().scaledToFit() },
                                                   image: { Image(uiImage: $0).resizable() })
                                            .frame(width: 30, height: 30)
                                        Text(review.author).font(.caption)
                                    }
                                    Text(review.content)
                                        .font(.caption)
                                        .onTapGesture(perform: {setShowMore(reviewId: review.id)})
                                        .lineLimit(reviewIdExpanded == review.id ? 100 : 3)
                                    HStack{
                                        Spacer()
                                        Text(reviewIdExpanded == review.id ? "See less" : "See more")
                                            .font(.caption).foregroundColor(.gray)
                                            .onTapGesture(perform: {setShowMore(reviewId: review.id)})
                                    }
                                }.padding()
                            }.overlay(
                                RoundedRectangle(cornerRadius: 10)
                                    .stroke(Color.gray, lineWidth: 1)
                            )
                        }
                    }
                }.padding().padding(.top,30)
            }
            .onAppear(perform: {loader.load()})
            .coordinateSpace(name: self.refreshName)
            .navigationBarTitle(movie!.originalTitle)
            .navigationBarItems(trailing:
                                    Image(favoriteImage)
                                    .resizable()
                                    .renderingMode(.template)
                                    .frame(width: 16, height: 16)
                                    .foregroundColor(.white)
                                    .onTapGesture(perform: {
                self.onFavoriteClicked()
            }))
        }
    }
    
    func setShowMore(reviewId: String){
        if (reviewId == reviewIdExpanded) { reviewIdExpanded = ""}
        else {reviewIdExpanded = reviewId}
    }
}

struct MovieDetailScreenContent_Previews: PreviewProvider {
    static var previews: some View {
        MovieDetailScreenContent(data: MovieDetailAndReview.companion.mocked, onRefresh: {}, isFavorited: false, onFavoriteClicked: {})
    }
}
